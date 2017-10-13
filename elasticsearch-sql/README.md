# elasticsearch sql

This module can be used to parse our SQL dialect into elasticsearch query model. Like all other language compilers, the structure of this project includes:

- **parser**, which translate the SQL dialect into a common internal query model
- **backend**, which translate the intermediate model into final json query model
