#!/bin/bash
echo 'hello world'
# Function to get new files added in the current branch
get_new_files() {
  # Get the name of the current branch
  current_branch=$(git branch --show-current)
  print $current_branch
  current_branch='testing'

  # Get the name of the default branch (usually 'main' or 'master')
  default_branch=$(git remote show origin | grep 'HEAD branch' | cut -d' ' -f5)

  # Fetch the latest changes from the remote
  git fetch testing

  # Get the list of new files added in the current branch compared to the default branch
  #new_files=$(git diff --name-only --diff-filter=A origin/$default_branch...$current_branch)
  git checkout master
  new_files=bash.sh

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
      echo "Scanning new file: $file"
      # Replace 'scan_tool' with your actual scan tool command
      snyk code test "$file" --severity-threshold=high --fail-on=all
    else
      echo "Skipping $file (not a regular file)"
    fi
  done
}

# Run the scan on new files
scan_new_files


