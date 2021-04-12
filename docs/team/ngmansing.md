# Sam Ng Man Sing - Project Portfolio Page

## Project: TraceYourProj

### Overview
TraceYourProj is a desktop app for **tracking online resources** for **data science projects**, optimized
for use via a Command Line Interface (CLI).

### Summary of Contributions
Given below are my contributions to the project.

#### Features implemented

* **Feature 1**: Added an add command.
    * <i>What it does</i> : Allows user to add a resource to a project.
    * <i>Justification</i> : This feature is a fundamental command of the product that is for users to add a resource to
  the target project.

* **Feature 2**: Added data feature implicitly
  * <i>What it does</i> : Updates the last modified data when the resource is added or edited
  * <i>Justification</i> : This feature improves the product significantly because it updates the last modified dates 
    automatically when a resource is added or edited such that users could know when they have created/modified 
    resources.

#### Other code implementation

* Code contributed: [RepoSense link](https://nus-cs2113-ay2021s2.github.io/tp-dashboard/?search=NgManSing&sort=groupTitle&sortWithin=title&since=2021-03-05&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false)
  Wrote fundamental classes such as CommandParse and InputParser
* Wrote JUnit tests and Integration tests related to CommandParse, InputParser and `add` feature.
* Wrote assertion, exception handling and logging related to `add` methods.

#### Contribution

<i>User Guide</i>
* Edited Quick Start, Notes about command, command summary and FAQ [[GitHub](https://ay2021s2-cs2113-w10-3.github.io/tp/UserGuide.html)]
* Added instructions, sample inputs & outputs for `add` feature. [[GitHub](https://ay2021s2-cs2113-w10-3.github.io/tp/UserGuide.html#add)]

<i>Developers Guide</i>
* Added documentations & sequence diagram for `add` feature. [[GitHub](https://ay2021s2-cs2113-w10-3.github.io/tp/DeveloperGuide.html#add)]
* Added Architecture and its diagram under **Design**.[Link](https://ay2021s2-cs2113-w10-3.github.io/tp/DeveloperGuide.html#design)
* Added `Logic Components` and its UML class diagram under **Design**.[Link](https://ay2021s2-cs2113-w10-3.github.io/tp/DeveloperGuide.html#design)

<i>Team-based tasks</i>
  * Set up the GitHub team organisation
  * Refactor source code
    * Try to make the code in line with coding standard
    * Adopt abstraction to split long methods into multiple short methods.
    * Encapsulate codes to from a large class into small classes.
    * Reduced coupling between classes (In previous design, both `Main` and `Logic` stores the address of `Model`. In the current version, only `Logic` stores the address of `Model`.)
    * Removing magic numbers/literals
  * Post/resolve issues in the Issue tracker
  * Released TraceYourProj v2.0
  * Developer Guide & User Guide:
    * Beautify Developer Guide.
    * Edit typo
    * Enrich content

* **Review/mentoring contributions:**
    * Reviewed PRs: [GitHub](https://github.com/AY2021S2-CS2113-W10-3/tp/pulls?q=state%3Aclosed+type%3Apr+reviewed-by%3ANgManSing)
    * Resolved minor conflicts from some PRs.
    * Provided my suggestions when teammates encountered problems. Example:
      ```
      Use print( ... + "\n") instead of println(...) to deal with different line separator problem.
      ```

* **Contributions beyond the project team:**
    * Post common question regarding IDE on the [forum](https://github.com/nus-cs2113-AY2021S2/forum/issues/14).
    * Provide suggestions for other team's product on [GitHub](https://github.com/nus-cs2113-AY2021S2/tp/pull/20/files/6a997527bc6fcbafea346f18d41fa926169dda38).