$SCRIPT_DIR = Split-Path -Parent $MyInvocation.MyCommand.Path

Set-Location(Join-Path $SCRIPT_DIR "..")

javac ExplorerMain.java
java ExplorerMain
