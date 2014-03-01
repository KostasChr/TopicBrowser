package gr.ntua.imu.topics.app.page;

import de.agilecoders.wicket.markup.html.bootstrap.heading.Heading;
import gr.ntua.imu.topics.BrowserService;
import gr.ntua.imu.topics.analyzer.Analyzer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.RangeValidator;

/**
 * @author KostasChr
 */
public class OptionsPage extends WebPage {

    @SpringBean
    private BrowserService browserService;
    private  FileUploadField fileUpload;

    private class InputForm extends Form<Analyzer> {
        /**
         * Construct.
         *
         * @param name Component name
         */
        @SuppressWarnings("serial")
        public InputForm(String name) {
            super(name, new CompoundPropertyModel<Analyzer>(browserService.getAnalyzer()));
            add(new TextField<Integer>("numberOfIterations").setRequired(true).add(
                    new RangeValidator<Integer>(0, 5000)));
            add(new TextField<Integer>("numberOfTopics").setRequired(true).add(
                    new RangeValidator<Integer>(0, 10000)));
            add(new TextField<Double>("alpha", Double.class).setRequired(true));
            add(new TextField<Double>("beta", Double.class).setRequired(true));
            add(new Button("saveButton"));

            add(new Button("resetButton") {
                @Override
                public void onSubmit() {
                    // just set a new instance of the page
                    setResponsePage(OptionsPage.class);
                }
            }.setDefaultFormProcessing(false));


        }

        @Override
        public void onSubmit() {
            setResponsePage(ResultPage.class);
        }
    }

    public OptionsPage() {
        Heading heading = new Heading("wicket-markup-id", Model.of("Topic Browser"));
        Heading heading2 = new Heading("h3", Model.of("Step 2: Select the model options"));
        add(heading);
        add(heading2);
        add(new InputForm("inputForm"));

    }

}
