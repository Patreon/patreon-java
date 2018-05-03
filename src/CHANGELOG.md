# 0.4.0
## Added
* Support for requesting optional fields from API objects which have them (Pledge, User).
These fields will be null on the retrieved resource object unless specifically requested.
* Resource Objects now have `[Resource]Field` enums.  These list all field, their names in the API, 
and whether they're retrieved by default.
* Added `.editorconfig`
* Support for specifying an alternate API endpoint, for testing.

##Fixed
* Improvements to javadoc

