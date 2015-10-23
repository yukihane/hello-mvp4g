# Launching Super Dev Mode

This sequence is GWT version 2.5.1 specific.

(see also: http://stackoverflow.com/a/18333050/4506703 )
 1. Execute: `mvn clean process-classes gwt:run-codeserver` .
 1. Open URL: http://localhost:9876/ .
 1. Register "Dev Mode On" and "Dev Mode off".
 1. Open link to http://localhost:9876/MainModule/ .
 1. Download *.gwt.rpc and move it to target/main-0.0.1-SNAPSHOT/MainModule/ .
 1. Execute on another terminal: `mvn gwt:run` .
 1. Open URL: http://localhost:8888/ .
 1. Push bookmarklet: "Dev Mode On" , and "Compile" .