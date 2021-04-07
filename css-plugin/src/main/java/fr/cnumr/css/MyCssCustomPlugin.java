package fr.cnumr.css;

import org.sonar.api.Plugin;

import java.util.Arrays;
import java.util.List;


public class MyCssCustomPlugin implements Plugin {

    @Override
    public void define(Context context) {

        // TODO rajouter les autres
        List<Class> list = Arrays.asList(MyCssCustomRulesDefinition.class);
        context.addExtensions(list);
       /*
        context.addExtensions(
                ImmutableList.of(
                        MyCssCustomRulesDefinition.class,
                        MyLessCustomRulesDefinition.class,
                        MyScssCustomRulesDefinition.class));

        */

    }

}