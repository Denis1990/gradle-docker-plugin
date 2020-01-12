package com.bmuschko.gradle.docker.tasks.volume

class DockerRemoveVolume extends DockerExistingVolume {
    @Override
    void runRemoteCommand() {
        logger.quiet "Removing volume '${volumeId.get()}'."
        dockerClient.removeVolumeCmd(volumeId.get()).exec()
    }
}
