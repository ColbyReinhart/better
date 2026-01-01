plugins {
	id("java-library")
}

repositories {
	mavenCentral()
}

tasks.compileJava {
	options.release = 21
}
