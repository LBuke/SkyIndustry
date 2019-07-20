# File System

This allows you to create, get, save files with ease.

---
### Useful
```java
void load();
T get();
void save();
void generate();

public final class YourFile extends BaseFile<Extension> {
    public YourFile() {
        super("/directory", "fileName", "extension")
    }
}
```

---
### Example Usage
```java
/* Yaml File */
YamlFile file = new YamlFile("/directory", "fileName");
FileConfiguration config = file.get();
String test = config.get("test");
config.set("test2", 123);
file.save();

/* Properties File */
PropertiesFile file = new PropertiesFile("/directory", "fileName");
Properties prop = file.get();
String test = prop.get("test");
```

---
### TODO List
> Create more type extensions, ei JSON, TXT, etc..


***
***authors:** Luke Bingham (**MrTeddeh**)*, ..