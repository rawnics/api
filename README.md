# Spring REST API 

## Workflow and commands for branching to develop and merging back to master repository

1. Initialize repository
  *      Initialize a repo : git init
  *      Add a remote repo : git remote add origin https://github.com/rawnics/api.git
  *      Pull the master   : git pull origin master
2. Branch checkout,
  *     List branches      : git ls-remote origin
  *     Checkout branch    : git checkout -b develop master
  *     Select branch      : git checkout develop
3. Code Changes       : echo "Development branch code base changed" >> README.md
  *       (3.1) Index      : git add .
  *       (3.2) Commit     : git commit -a -m "Development branch code base"
  *       (3.3) Remote push: git push origin develop
4. Merge
  *     (4.1) Compare branch : git diff master develop
  *     (4.2) Merge develop  : git checkout master
  *                            git merge develop
  *     (4.3) Remote push    : git push
  *     (4.4) Delete branch  : git branch -d develop
5. Create feature product branch
6. Tag branch version
  *    (6.1) Tag annotated : git tag "0.1" -m "Beta 0.1"
  *    (6.2) Push to tag   : git push origin tag "0.1"
  *                          git push origin --tags
  *    (6.3) Checkout tag  : git checkout "0.1"
	
	

