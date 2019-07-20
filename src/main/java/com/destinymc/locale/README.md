# i18n

This allows you to localise messages with ease.

---
### Useful
```java
String get(Locale locale, String key);
String get(Locale locale, String key, StringMap data);
String[] getAsArray(Locale locale, String key);
String[] getAsArray(Locale locale, String key, StringMap data);
List<String> getAsList(Locale locale, String key);
List<String> getAsList(Locale locale, String key, StringMap data);
```

---
### Example Usage
```java
/* Simple localised message */
String str = I18n.get(Locale.EN_GB, "test");

/* Localised message with data tags */
String str = I18n.get(Locale.EN_GB, "test", new StringMap().put("rank", "DEV").put("level", 2));

/* Simple localised message (Array & List) */
String[] str = I18n.getAsArray(Locale.EN_GB, "test");
List<String> str = I18n.getAsList(Locale.EN_GB, "test");

/* Localised message with data tags (Array & List) */
String[] str = I18n.getAsArray(Locale.EN_GB, "test", new StringMap().put("rank", "DEV"));
List<String> str = I18n.getAsList(Locale.EN_GB, "test", new StringMap().put("level", 2));
```

---
### TODO List
> Create method for auto 'sizing' lore's


***
***authors:** Luke Bingham (**MrTeddeh**)*, ..