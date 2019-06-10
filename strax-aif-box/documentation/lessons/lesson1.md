# Lesson 1

## Git
[Git Reference](https://git-scm.com/)

[Atlassian Git Reference](https://www.atlassian.com/git/tutorials/what-is-version-control)

### Gitflow
Branching model
Master and Develop branch and feature branches. 
https://datasift.github.io/gitflow/IntroducingGitFlow.html

#### Git Visualization
[sourcetree](https://www.sourcetreeapp.com/)
##### Standalone program

[git graph](https://marketplace.visualstudio.com/items?itemName=mhutchie.git-graph)
##### Visual Studio Extension

### Commands:

#### Git Clone
- Clones a repository into a newly created directory, creates remote-tracking branches for each branch in the cloned repository (visible using git branch -r), and creates and checks out an initial branch that is forked from the cloned repository’s currently active branch. [source](https://git-scm.com/docs/git-clone)
##### Command: `git clone`

#### Git Pull
- The git pull command is used to fetch and download content from a remote repository and immediately update the local repository to match that content. [source](https://www.atlassian.com/git/tutorials/syncing/git-pull)
##### Command: `git pull`

#### Git Fetch
- The git fetch command downloads commits, files, and refs from a remote repository into your local repo. 
- Fetching is what you do when you want to see what everybody else has been working on. 
- When downloading content from a remote repo, git pull and git fetch commands are available to accomplish the task. 
- You can consider git fetch the 'safe' version of the two commands. It will download the remote content but not update your local repo's working state, leaving your current work intact.
[source](https://www.atlassian.com/git/tutorials/syncing/git-fetch)
##### Command: `git fetch`

#### Git Push
- The git push command is used to upload local repository content to a remote repository. [source](https://www.atlassian.com/git/tutorials/syncing/git-push)
- If no remote branch, it will return a command to create an origin/cloud branch. You will then have to run that command to create the origin/cloud branch. Then progress as normal with git push.
##### Command: `git push`

#### Git Branch
- Without any arguments, lists all branches in the repository. And current branch is highlighted with an asterisk.
##### Command: `git branch <branch>`

#### Git Branch \<branch>
- The command’s second form creates a new branch head named \<branchname> which points to the current HEAD, or \<start-point> if given.
- Note that this will create the new branch, but it will not switch the working tree to it; use "git checkout \<newbranch>" to switch to the new branch.
##### Command: `git branch <branch>`

SIDENOTE: HEAD is a reference to the last commit in the currently check-out branch. [source](http://researchhubs.com/post/computing/git/what-is-HEAD-in-git.html)

#### Git Checkout
- Switches branches. Switches Versions of the repository.
##### Command: `git checkout <branch>`

#### Pull Requests
Pull requests let you tell others about changes you've pushed to a GitHub repository. Once a pull request is sent, interested parties can review the set of changes, discuss potential modifications, and even push follow-up commits if necessary. 
[Source](https://yangsu.github.io/pull-request-tutorial/)

### Exercises

1. [Exercise 1](https://github.com/GroupCareTech/strax-qa/blob/lb-strax-aif/strax-aif-box/documentation/exercises/exercise1.md)

