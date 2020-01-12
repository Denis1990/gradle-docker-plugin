package com.bmuschko.gradle.docker.tasks.volume

import com.bmuschko.gradle.docker.AbstractGroovyDslFunctionalTest

class DockerVolumeFunctionalTest extends AbstractGroovyDslFunctionalTest {

    def 'Can create and remove a docker volume'() {
        given:
        final uniqueVolumeName = createUniqueContainerName()
        buildFile << """
            import com.bmuschko.gradle.docker.tasks.volume.*;
            task createVolume(type: DockerCreateVolume) {
                volumeId = "${uniqueVolumeName}"
            }

            task removeVolume(type: DockerRemoveVolume) {
                targetVolumeId createVolume.getVolumeId()
            }

//            task inspectVolume(type: DockerInspectVolume) {
//                targetVolumeId createVolume.getVolumeId()
//                dependsOn createVolume
//
//                onNext { volume ->
//                    println 'inspectVolume ' + volume.name
//                }
//            }

//            task inspectNoVolume(type: DockerInspectVolume) {
//                targetVolumeId createVolume.getVolumeId()
//                dependsOn removeVolume
//
//                onError { error ->
//                    println 'inspectNoVolume ' + error
//                }
//            }

//            removeVolume.mustRunAfter inspectVolume
            createVolume.finalizedBy removeVolume
        """
    }
}
