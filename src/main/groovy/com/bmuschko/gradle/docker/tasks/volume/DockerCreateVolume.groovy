package com.bmuschko.gradle.docker.tasks.volume

import com.bmuschko.gradle.docker.tasks.AbstractDockerRemoteApiTask
import com.github.dockerjava.api.command.CreateVolumeResponse
import groovy.transform.CompileStatic
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input

@CompileStatic
class DockerCreateVolume extends AbstractDockerRemoteApiTask {
    @Input
    final Property<String> volumeId = project.objects.property(String)

    @Override
    void runRemoteCommand() {
        logger.quiet "Creating volume '${volumeId.get()}'."
        CreateVolumeResponse volume = dockerClient.createVolumeCmd().withName(volumeId.get()).exec()

        if (nextHandler) {
            nextHandler.execute(volume)
        }
    }
}
