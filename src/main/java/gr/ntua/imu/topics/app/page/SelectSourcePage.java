package gr.ntua.imu.topics.app.page;

import gr.ntua.imu.topics.BrowserService;
import gr.ntua.imu.topics.analyzer.Analyzer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.RangeValidator;

public class SelectSourcePage extends WebPage {

    @SpringBean
    private BrowserService browserService;


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
                    setResponsePage(SelectSourcePage.class);
                }
            }.setDefaultFormProcessing(false));


        }

        @Override
        public void onSubmit() {
            setResponsePage(ResultPage.class);
        }
    }

    public SelectSourcePage() {
        add(new InputForm("inputForm"));

    }

}
