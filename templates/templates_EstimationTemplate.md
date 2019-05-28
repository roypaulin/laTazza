<<<<<<< HEAD
﻿# Project Estimation  templateAuthors:Date:Version:# Contents- [[Data from your LaTazza project]- [Estimate by product decomposition]- [Estimate by activity decomposition ]# Data from your LaTazza project###|         Total person days worked by your  team, considering period March 5 to April 26 (1 person day == 8 person hours)     |   || ----------- | ------------------------------- ||Total Java LoC delivered on April 26 (only code, no Junit code) |1138 || Total number of Java classes delivered on April 26 (only code, no Junit code)|10 || Productivity P =|1138/63 ||Average size of Java class A = | 1138/10 |**nb:** we had used an automated command for calculating that lines, it isreplicable and works also in the future, following the code:```bashcd src/main && find . -name *.java -exec grep [a-zA-Z0-9{}] {} \; | wc -l```# Estimate by product decomposition###|             | Estimate                        || ----------- | ------------------------------- || Estimated n classes   |                             || Estimated LOC per class  (Here use Average A computed above )      |                            || Estimated effort  (person days) (Here use productivity P)  |                                      || Estimated calendar time (calendar weeks) (Assume team of 4 people, 8 hours per day, 5 days per week ) |                    |# Estimate by activity decomposition###|         Activity name    | Estimated effort    || ----------- | ------------------------------- || | |###Insert here Gantt chart with above activities
=======
# Project Estimation  template

Authors:

Date:

Version:

# Contents

- [[Data from your LaTazza project]

- [Estimate by product decomposition]
- [Estimate by activity decomposition ]



# Data from your LaTazza project

###

|         Total person days worked by your  team, considering period March 5 to April 26 (1 person day == 8 person hours)     |   |
| ----------- | ------------------------------- |
|Total Java LoC delivered on April 26 (only code, no Junit code) | |
| Total number of Java classes delivered on April 26 (only code, no Junit code)| |
| Productivity P =| |
|Average size of Java class A = | |

**nb:** we had used an automated command for calculating that lines, it is
replicable and works also in the future, following the code:
```bash
cd src/main && find . -name *.java -exec grep [a-zA-Z0-9{}] {} \; | wc -l
```

# Estimate by product decomposition



###

|             | Estimate                        |
| ----------- | ------------------------------- |
| Estimated n classes   |                             |
| Estimated LOC per class  (Here use Average A computed above )      |                            |
| Estimated effort  (person days) (Here use productivity P)  |                                      |
| Estimated calendar time (calendar weeks) (Assume team of 4 people, 8 hours per day, 5 days per week ) |                    |


# Estimate by activity decomposition



###

|         Activity name    | Estimated effort    |
| ----------- | ------------------------------- |
| | |

### gantt example

gantt
    title A Gantt Diagram
    dateFormat  YYYY-MM-DD
    section Section
    A task           :a1, 2014-01-01, 30d
    Another task     :after a1  , 20d
    section Another
    Task in sec      :2014-01-12  , 12d
    another task      : 24d

###
Insert here Gantt chart with above activities
>>>>>>> 2869da0884d9dfa1b77bda6025004f90ff5951bb
