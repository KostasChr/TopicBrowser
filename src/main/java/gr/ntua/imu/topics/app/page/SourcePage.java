package gr.ntua.imu.topics.app.page;

import de.agilecoders.wicket.markup.html.bootstrap.heading.Heading;
import gr.ntua.imu.topics.data.FileSource;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.lang.Bytes;

import java.io.File;
import java.io.IOException;

/**
 * @author KostasChr
 */
public class SourcePage extends WebPage {

    @SpringBean
    private FileSource fileSource;

    private FileUploadField fileUpload;

    private class FileUploadForm extends Form {

        private static final long serialVersionUID = 1L;

        private FileUploadField fileUploadField;


        public FileUploadForm(String name) {
            super(name);

            setMultiPart(true);
            setOutputMarkupId(true);
            setMaxSize(Bytes.gigabytes(1));


            add(fileUploadField = new FileUploadField("fileInput"));
            add(new Button("saveButton"));

        }

        @Override
        public void onSubmit() {

            super.onSubmit();

            final FileUpload upload = fileUploadField.getFileUpload();

            if (upload == null)
                return;

            File tempFile;

            try {
                tempFile = upload.writeToTempFile();
            } catch (IOException e) {
                throw new RuntimeException("Unable to write file upload.", e);
            }

            fileSource.setFilePath(tempFile.getAbsolutePath());


            setResponsePage(OptionsPage.class);
        }
    }

    public SourcePage() {
        Heading heading = new Heading("wicket-markup-id", Model.of("Heading Title"));
        add(heading);
        add(new FileUploadForm("fileUploadForm"));

    }

}
