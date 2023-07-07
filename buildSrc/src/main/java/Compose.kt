object Compose {

    val kotlinCompilerExtensionVersion = "1.4.6"

    private val composeBomVersion = "2022.10.00"

    val bom = "androidx.compose:compose-bom:$composeBomVersion"
    val ui = "androidx.compose.ui:ui"
    val uiGraphics = "androidx.compose.ui:ui-graphics"
    val uiToolingPreview = "androidx.compose.ui:ui-tooling-preview"
    val material3 = "androidx.compose.material3:material3"

    private val activityComposeVersion = "1.5.1"
    val activityCompose = "androidx.activity:activity-compose:$activityComposeVersion"


    private const val navigationVersion = "2.5.3"
    const val navigation = "androidx.navigation:navigation-compose:$navigationVersion"

    private const val hiltNavigationComposeVersion = "1.0.0-beta01"
    const val hiltNavigationCompose = "androidx.hilt:hilt-navigation-compose:$hiltNavigationComposeVersion"

}