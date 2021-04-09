package fr.cnumr.css;


import org.sonar.api.server.rule.RulesDefinition;

import org.sonar.plugins.css.css.CssLanguage;
import org.sonarsource.analyzer.commons.RuleMetadataLoader;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

public class MyCssCustomRulesDefinition implements RulesDefinition {

    public static final String REPOSITORY_KEY = "css";
    public static final String RULE_REPOSITORY_NAME = "mycompany-css";

    // TODO rename
    public static final String RESOURCE_FOLDER = "org/sonar/l10n/css/rules/";

    // Add the rule keys of the rules which need to be considered as template-rules
    private static final Set<String> RULE_TEMPLATES_KEY = Collections.emptySet();
    @Override
    public void define(Context context) {
        NewRepository repository = context
                .createRepository(REPOSITORY_KEY, CssLanguage.KEY)
                .setName(RULE_REPOSITORY_NAME);

        RuleMetadataLoader ruleMetadataLoader = new RuleMetadataLoader(RESOURCE_FOLDER + REPOSITORY_KEY);
        ruleMetadataLoader.addRulesByAnnotatedClass(repository, CssRules.getRuleClasses());
        setTemplates(repository);
        repository.done();

        // StylelintReportSensor.getStylelintRuleLoader().createExternalRuleRepository(context);
    }

    private static void setTemplates(NewRepository repository) {
        RULE_TEMPLATES_KEY.stream()
                .map(repository::rule)
                .filter(Objects::nonNull)
                .forEach(rule -> rule.setTemplate(true));
    }
}