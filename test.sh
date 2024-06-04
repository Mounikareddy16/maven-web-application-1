#!/bin/bash

TARGET_DIR="Changedfiles"

if [ ! -d "$TARGET_DIR" ]; then
    mkdir -p $TARGET_DIR
else
   rm -rf $TARGET_DIR
   mkdir -p $TARGET_DIR
fi
# Function to get new files added in the current branch
get_new_files() {
  # Get the name of the current branch
  git checkout -f test1
  current_branch=$(git branch --show-current)
  echo $current_branch

  # Get the name of the default branch (usually 'main' or 'master')
  default_branch="master"
  #default_branch=$(git remote show origin | grep 'HEAD branch' | cut -d' ' -f5)

  # Fetch the latest changes from the remote
  git fetch origin

  # Get the list of new files added in the current branch compared to the default branch
  #new_files=$(git diff --name-only --diff-filter=A origin/master..testing)
  new_files=$(git diff --name-only --diff-filter=A origin/$default_branch...$current_branch)
  
  echo "$new_files"
}

# Function to scan new files
scan_new_files() {
  new_files=$(get_new_files)
  
  if [ -z "$new_files" ]; then
    echo "No new files to scan."
    exit 0
  fi

  # Iterate over the new files and scan each one
  for file in $new_files; do
    if [ -f "$file" ]; then
      echo "copying new file: $file"
      cp "$file" "$TARGET_DIR/$file"
      # Replace 'scan_tool' with your actual scan tool command
    else
      echo "Skipping $file (not a regular file)"
    fi
  done
}

snyk_scan_new_files() {
if [ -z "$(ls -A $TARGET_DIR)" ]; then
    snyk code test --severity-threshold=high --fail-on=all #first
    echo $?
else
    snyk code test $TARGET_DIR --severity-threshold=high --fail-on=all
    echo $?
fi
}
# Run the scan on new files
get_new_files
scan_new_files
snyk_scan_new_files
