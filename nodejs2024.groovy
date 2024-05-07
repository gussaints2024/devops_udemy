job('Aplicacion Node.js DSL') {
    description('Aplicación Node JS DSL para el curso de Jenkins')
    scm {
        git('https://github.com/macloujulian/nodejsapp.git', 'master') { node ->
            node / gitConfigName('macloujulian')
            node / gitConfigEmail('macloujulian@gmail.com')
        }
    }
    triggers {
        scm('H/7 * * * *')
    }
    wrappers {
        nodejs('nodejs')
    }
    steps {
        shell("npm install")
    }
    publishers {
        // Add conditional post-build actions.
        flexiblePublish {
            // Adds a conditional action.
            conditionalAction {
                // Specifies the condition to evaluate before executing the build steps.
                condition {
                    // Runs a shell script for checking the condition.
                    shell('echo hello0000!')
                    // Runs the build steps if the current build status is within the configured range.
                    status('FAILURE', 'SUCCESS')
                }
                steps {
                    shell('echo hello1111!')
                }
            }
        }
    }
}
