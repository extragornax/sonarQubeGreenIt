package fr.cnumr.css;


import org.sonar.api.server.rule.RulesDefinition;

import org.sonar.plugins.css.css.CssLanguage;
import org.sonarsource.analyzer.commons.RuleMetadataLoader;

public class MyCssCustomRulesDefinition implements RulesDefinition {

    public static final String REPOSITORY_KEY = "css";
    public static final String RULE_REPOSITORY_NAME = "SonarAnalyzer";

    // TODO rename
    public static final String RESOURCE_FOLDER = "org/sonar/l10n/css/rules/";

    @Override
    public void define(Context context) {
        NewRepository repository = context
                .createRepository(REPOSITORY_KEY, CssLanguage.KEY)
                .setName(RULE_REPOSITORY_NAME);

        RuleMetadataLoader ruleMetadataLoader = new RuleMetadataLoader(RESOURCE_FOLDER + REPOSITORY_KEY);
        ruleMetadataLoader.addRulesByAnnotatedClass(repository, CssRules.getRuleClasses());
        repository.done();

       // StylelintReportSensor.getStylelintRuleLoader().createExternalRuleRepository(context);
    }
}