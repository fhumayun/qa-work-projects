# Get develop branch sha1 to fork new branch from
VERSIONSTRING=<PUT VERSION HERE Ex STX.0.999.20161231.sac>

# Call Jira to create new Verion.
curl -v -g -X POST -H "Content-Type: application/json" -H "Authorization: Basic Zmh1bWF5dW5AZ3JvdXBjYXJldGVjaC5jb206Y2FyZXRlYW0=" -H "Cache-Control: no-cache" \
 -d '{
	"project": "STX",
	"name" : "'${VERSIONSTRING}'",
	"description": "",
	"userReleaseDate": "",
	"userStartDate": ""
}' "https://eagleeyeintel.atlassian.net/rest/api/2/version"
