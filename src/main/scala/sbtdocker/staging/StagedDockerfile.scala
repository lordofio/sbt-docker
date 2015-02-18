package sbtdocker.staging

import sbt._
import sbtdocker.{SourceFile, DockerInstruction}

object StagedDockerfile {
  def empty = StagedDockerfile(Seq.empty, Set.empty)
}

case class StagedDockerfile(instructions: Seq[DockerInstruction], stageFiles: Set[(SourceFile, File)]) {
  def addInstruction(instruction: DockerInstruction) = copy(instructions = instructions :+ instruction)

  def stageFile(source: SourceFile, destination: File) = copy(stageFiles = stageFiles + (source -> destination))

  def stageFiles(files: Set[(SourceFile, File)]) = copy(stageFiles = stageFiles ++ files)

  def instructionsString = instructions.mkString("\n")
}
