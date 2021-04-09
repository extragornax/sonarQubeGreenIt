package fr.cnumr.css;


import org.junit.Test;
import org.sonar.api.Plugin;
import org.sonar.api.SonarEdition;
import org.sonar.api.SonarQubeSide;
import org.sonar.api.SonarRuntime;
import org.sonar.api.internal.SonarRuntimeImpl;
import org.sonar.api.utils.Version;

import static org.assertj.core.api.Assertions.assertThat;

public class CssPluginTest {

    @Test
    public void count_extensions() {
        SonarRuntime runtime = SonarRuntimeImpl.forSonarQube(Version.create(7, 9), SonarQubeSide.SCANNER, SonarEdition.COMMUNITY);
        Plugin.Context context = new Plugin.Context(runtime);
        Plugin underTest = new MyCssCustomPlugin();
        underTest.define(context);
        assertThat(context.getExtensions()).hasSize(1);
    }

    @Test
    public void count_extensions_sonarlint() {
        SonarRuntime runtime = SonarRuntimeImpl.forSonarLint(Version.create(7, 9));
        Plugin.Context context = new Plugin.Context(runtime);
        Plugin underTest = new MyCssCustomPlugin();
        underTest.define(context);
        assertThat(context.getExtensions()).hasSize(1);
    }
}
