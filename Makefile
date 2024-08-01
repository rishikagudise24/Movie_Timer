all: runTests

runTests: FrontendDeveloperTests.class
	java -cp .:../junit5.jar org.junit.platform.console.ConsoleLauncher --class-path . --select-class FrontendDeveloperTests

FrontendDeveloperTests.class: FrontendDeveloperTests.java FrontendInterface.class FrontendImplementation.class TextUITester.class BackendInterface.class BackendImplementation.class
	javac -cp .:../junit5.jar $<

FrontendInterface.class: FrontendInterface.java
	javac $<

FrontendImplementation.class: FrontendImplementation.java FrontendInterface.class BackendInterface.class
	javac $<

TextUITester.class: TextUITester.java
	javac $<

BackendInterface.class: BackendInterface.java
	javac $<

BackendImplementation.class: BackendImplementation.java BackendInterface.class
	javac $<

clean:
	rm -f *.class
