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
|Total Java LoC delivered on April 26 (only code, no Junit code) |1698 |
| Total number of Java classes delivered on April 26 (only code, no Junit code)|10 |
| Productivity P =|1698/63=26.82 |
|Average size of Java class A = | 1698/10=169.8 |

**nb:** we had used an automated command for calculating that lines, it is
replicable and works also in the future, following the code:
```bash
cd src/main && find . -name *.java -exec grep '^.*$' {} \; | wc -l
```

<!--
    to keep only the non blank lines:
    cd src/main && find . -name *.java -exec grep [a-zA-Z0-9{}] {} \; | wc -l
-->

# Estimate by product decomposition

### module database

| database module            | Estimate                        |
| ----------- | ------------------------------- |
| Estimated n classes   |         1                    |
| Estimated LOC per class      |       515                       |
| Estimated effort  (person days)   |     $$\frac{515}{26.82}=19.2$$                           |
| Estimated calendar time (calendar weeks) (Assume team of 4 people, 8 hours per day, 5 days per week ) |          less than one          |


### module data binding

|             | Estimate                        |
| ----------- | ------------------------------- |
| Estimated n classes   |              9               |
| Estimated LOC per class        |             $$\frac{1183}{26.82}=44.1$$                |
| Estimated effort  (person days) (Here use productivity P)  |                                      |
| Estimated calendar time (calendar weeks) (Assume team of 4 people, 8 hours per day, 5 days per week ) |   0.275 so again less than one                 |




# Estimate by activity decomposition



###

|         Activity name    | Estimated effort    |
| ----------- | ------------------------------- |
| database module creation | 19%  |
| database module testing | 22% |
| data binding module creation | 20% |
| data binding module testing | 21% |
| merge of work | 1% |
| system test | 7% |
| acceptance test | 8% |
| gui test | 2% |


###
Insert here Gantt chart with above activities







