# Cirrostratus
A straightforward tag cloud generator, written in Java

## Building
Run `mvn clean package`, find your `.jar` file in `target/` folder.

## Usage
See the `java/test/` directory for examples. `App.java` can be used as a default entry point, pass in a URL to generate the tag cloud from.

## Customization
See options in `DefaultBlueCloud.java`. You can implement your down TagCloud specs via the `Cloud` interface.

## Note
This project is not maintained, but feel free to adapt/change in any way.
