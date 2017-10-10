# elasticsearch jdbc

[![Build Status](https://api.travis-ci.org/syhily/elasticsearch-jdbc.svg?branch=master)](https://travis-ci.org/syhily/elasticsearch-jdbc/branches)
[![Coverage Status](https://coveralls.io/repos/github/syhily/elasticsearch-jdbc/badge.svg?branch=master&service=github)](https://coveralls.io/github/syhily/elasticsearch-jdbc?branch=master)
[![License](https://img.shields.io/:license-mit-brightgreen.svg)](https://opensource.org/licenses/MIT)

ElasticSearch has a user friendly HTTP-JSON interface. But sometimes, a jdbc based SQL interface would make it easy for
Java developers.

This project can be used to transform our SQL query into a normal elasticsearch query model. Currently in heavy development.

## Architecture

* **elasticsearch-sql** - Parse elasticsearch SQL dialect into elasticsearch query model.
* **elasticsearch-driver** - Wrap elasticsearch-sql into a jdbc driver.

## Development

### Tools

1. Java 1.8.131+
2. Maven 3.5.0 or above
3. IntelliJ IDEA 2017.2
4. Lombok Plugin

### Flow

1. clone the project

```bash
git clone https://github.com/syhily/elasticsearch-jdbc.git
```

2. compile the project and make all test passed

```bash
mvn clean package
```

3. the packed jar would located in target directory

## ElasticSearch SQL Definition

// TODO

## User Feedback

### Issues

If you have any problems with or questions about this program, please contact us through a [GitHub issue](https://github.com/syhily/elasticsearch-jdbc/issues).

### Contributing

You are invited to contribute new features, fixes, or updates, large or small; we are always thrilled to receive pull requests,
and do our best to process them as fast as we can.

Before you start to code, we recommend discussing your plans through a GitHub issue, especially for more ambitious contributions.
This gives other contributors a chance to point you in the right direction, give you feedback on your design,
and help you find out if someone else is working on the same thing.
