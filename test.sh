#!/bin/bash

# Fetch the latest branches and commits from the remote
git fetch

# Get the current branch name
current_branch=$(git rev-parse --abbrev-ref HEAD)

# Get the base branch (assuming master/main as the base branch)
base_branch="master"
if ! git show-ref --quiet refs/heads/$base_branch; then
  base_branch="master"
fi

# Check if the current branch is newly created by checking for its merge base
if git merge-base --is-ancestor $base_branch $current_branch; then
  echo "Branch '$current_branch' is not newly created."
  exit 0
fi

# List newly added files in the new branch compared to the base branch
new_files=$(git diff --name-only --diff-filter=A $base_branch...$current_branch)

if [ -z "$new_files" ]; then
  echo "No new files added in the branch '$current_branch'."
  exit 0
fi

# Perform actions on newly added files
echo "Newly added files in branch '$current_branch':"
for file in $new_files; do
  echo $file
  # Add your custom actions here, for example:
  # ./build_script.sh $file
done

# Example: Trigger a build for each newly added file (pseudo-command)
for file in $new_files; do
  echo "Triggering build for $file"
  # ./your_build_tool $file
done
