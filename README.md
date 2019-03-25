FRC 5687 The Outliers 2019 Chassisbot
---

This project is a very basic Java implementation for a drivetrain-only FRC robot.  It is intended as a starting point for new members of our programming team, but we also encourage new programmers on any team to take advantage of it!

Please note that while much of our project and code structure follows typical FRC patterns, we do have our own approaches to many aspects.  We make every effort to highlight those differences in our comments and in this readme.  We also use a slightly non-standard set of tools, which are outlined below.

Tools
---
We use Jaci's excellent gradlerio toolset in combination with JetBrains' IntelliJ, Atlassian's Sourcetree, and (obviously) GitHub to manage our codebase.  There are free downloads of each of the tools we use, although some do require registration.

Standard FRC Java downloads:

Primary tools we use:
[IntelliJ](https://www.jetbrains.com/idea/download/) - our primary IDE (used in place of Eclipse / VS Code)
[Gradle](https://gradle.org/releases/) - Used by Jaci's gradlerio to build the code and deploy it to the roborio
[Git](https://git-scm.com/downloads) - Source-code manager
[Sourcetree](https://www.sourcetreeapp.com/) - GUI for git.  This is optional, but all of our explanations will use it.
[TortoiseMerge](https://tortoisesvn.net/downloads.html) - GUI for resolving merge conflicts when different programmers make changes to the same file.  Note that this is installed as part of TortoiseSVN, but we only use the TortoiseMerge component.

Additional tools:
[WinSCP](https://tortoisesvn.net/downloads.html) - used for remote file access to the roborio (and Raspberry Pi if you use one)
[Putty](https://www.putty.org/) - used for remote terminal access to the roborio (and Raspberry Pi if you use one)
