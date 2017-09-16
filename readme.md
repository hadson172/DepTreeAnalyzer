# DepTreeAnalyzer
Simple Application which allow you find out which dependencies from two dependency trees were updated (to which version, scope), added, removed.

## Getting started
To run project you must set up properties file and few properties listed bellow.

### Prerequisites
* [Maven]((https://maven.apache.org/))


### Installing
* Create **app.properties** file in resources directory
* Setup properties:

```
maven.home=E:\\apache-maven-3.5.0-bin\\apache-maven-3.5.0
maven.source.project=Repo
maven.target.project=.
```

* **maven.home** - path to maven application directory
* **maven.source.project** - path to maven project from which will be taken source dependency tree
* **maven.target.project** - path to maven project from which will be taken target dependency tree 