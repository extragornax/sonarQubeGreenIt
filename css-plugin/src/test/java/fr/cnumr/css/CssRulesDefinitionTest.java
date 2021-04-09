package fr.cnumr.css;


import org.junit.Test;
import org.sonar.api.server.rule.RulesDefinition;

import static org.assertj.core.api.Assertions.assertThat;

public class CssRulesDefinitionTest {

    @Test
    public void test_with_external_rules() {
        MyCssCustomRulesDefinition rulesDefinition = new MyCssCustomRulesDefinition();
        RulesDefinition.Context context = new RulesDefinition.Context();
        rulesDefinition.define(context);

        assertThat(context.repositories()).hasSize(1);
        RulesDefinition.Repository mainRepository = context.repository(MyCssCustomRulesDefinition.REPOSITORY_KEY);

        assertThat(mainRepository.name()).isEqualTo(MyCssCustomRulesDefinition.RULE_REPOSITORY_NAME);
        assertThat(mainRepository.language()).isEqualTo("css");
        assertThat(mainRepository.isExternal()).isEqualTo(false);
        assertThat(mainRepository.rules()).hasSize(CssRules.getRuleClasses().size());

    }
}
