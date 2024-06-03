#!/bin/bash

# Fetch the latest branches and commits from the remote
git fetch

# Get the current branch name
current_branch=$(git rev-parse --abbrev-ref HEAD)

# List newly added files in the new branch compared to the base branch
new_files=$(git diff --name-only --diff-filter=A $base_branch...$current_branch)

if [ -z "$new_files" ]; then
  echo "No new files added in the branch '$current_branch'."
  exit 0
fi


