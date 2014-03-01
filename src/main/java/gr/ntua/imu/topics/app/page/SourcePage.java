package gr.ntua.imu.topics.app.page;

import de.agilecoders.wicket.markup.html.bootstrap.heading.Heading;
import de.agilecoders.wicket.markup.html.bootstrap.html.ChromeFrameMetaTag;
import de.agilecoders.wicket.markup.html.bootstrap.html.HtmlTag;
import de.agilecoders.wicket.markup.html.bootstrap.html.MetaTag;
import de.agilecoders.wicket.markup.html.bootstrap.html.OptimizedMobileViewportMetaTag;
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
        add(new HtmlTag("html"));

        add(new OptimizedMobileViewportMetaTag("viewport"));
        add(new MetaTag("description", Model.of("description"), Model.of("Topic Browser")));
        add(new MetaTag("author", Model.of("author"), Model.of("KostasChr")));
        add(new ChromeFrameMetaTag("chrome-frame"));
        //add header panel               `

        Heading heading = new Heading("wicket-markup-id", Model.of("Topic Browser"));
        Heading heading2 = new Heading("h3", Model.of("Step 1: Upload the file"));
        add(heading);
        add(heading2);
        add(new FileUploadForm("fileUploadForm"));

    }

}
