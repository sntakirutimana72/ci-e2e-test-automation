#!/bin/bash

# ==========================
# 1. Install xmllint if needed
# ==========================
if ! command -v xmllint &> /dev/null; then
  echo "Installing libxml2-utils..."
  apt-get update && apt-get install -y libxml2-utils
fi

# ==========================
# 2. Define browser matrix
# ==========================
browsers=(
  "chrome:128.0"
  "firefox:125.0"
)

# ==========================
# 2.1. Make general reports directory
# ==========================
mkdir -p reports/{allure,badges}

ALLURE_RESULTS_DIR="reports/allure"
BADGES_DIR="reports/badges"

# ==========================
# 3. Run tests for each browser
# ==========================
for b in "${browsers[@]}"; do
  browser=$(echo "$b" | cut -d: -f1)
  version=$(echo "$b" | cut -d: -f2)

  echo "=== Running tests for $browser $version ==="

  # Make allure report directory for current browser and version
  ALLURE_BROWSER_RESULTS_DIR="$ALLURE_RESULTS_DIR/${browser}_${version}"
  mkdir -p "$ALLURE_BROWSER_RESULTS_DIR"

  # Run Maven tests, saving reports
  set +e
  mvn clean test \
    -Dbrowser="$browser" \
    -Dbrowser.version="$version" \
    -Dallure.results.directory="$ALLURE_BROWSER_RESULTS_DIR"
  set -e

  echo "=== Finished $browser $version ==="

  # ==========================
  # 4. Generate custom badge
  # ==========================
  TOTAL=0
  FAILS=0

  for file in target/surefire-reports/*.xml; do
    if [ -f "$file" ]; then
      t=$(xmllint --xpath "count(//testcase)" "$file" 2>/dev/null)
      t=${t%.*}
      TOTAL=$((TOTAL + t))

      f=$(xmllint --xpath "count(//testcase[failure or error])" "$file" 2>/dev/null)
      f=${f%.*}
      FAILS=$((FAILS + f))
    fi
  done

  PASSED=$((TOTAL - FAILS))

  if [ "$TOTAL" -gt 0 ]; then
    PERCENT=$((100 * PASSED / TOTAL))
  else
    PERCENT=0
  fi

  if [ "$PERCENT" -ge 85 ]; then
    COLOR="brightgreen"
  elif [ "$PERCENT" -ge 65 ]; then
    COLOR="yellow"
  else
    COLOR="red"
  fi

  echo "{\"schemaVersion\":1,\"label\":\"$browser:$version Tests\",\"message\":\"$PASSED/$TOTAL ($PERCENT%)\",\"color\":\"$COLOR\"}" \
    > "$BADGES_DIR/$browser-$version.json"
done
