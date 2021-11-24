package demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Parsers {

    public static String FormatXml(List<Object> items) {
        StringBuilder tasksXml = new StringBuilder();
        tasksXml.append("<tasks>");

        items.forEach(task ->
        {
            tasksXml.append("\n\t<task>");
            ((Map<String, Object>) task).forEach((key, value) -> {
                tasksXml.append(String.format("\n\t\t<%s>\n", key));
                tasksXml.append(String.format("\t\t\t%s", value));
                tasksXml.append(String.format("\n\t\t</%s>", key));
            });
            tasksXml.append("\n\t</task>");
        });
        tasksXml.append("\n</tasks>");

        return tasksXml.toString();
    }

    public static String FormatCsv(List<Object> values) {
        List<String> headers = new ArrayList<>();
        ((Map<String, Object>) values.get(0)).forEach((key, value) -> headers.add(key));
        StringBuilder tasksCsv = new StringBuilder();
        headers.forEach(field -> tasksCsv.append(String.format("%s,", field)));
        tasksCsv.deleteCharAt(tasksCsv.length() - 1);
        values.forEach(task ->
        {
            tasksCsv.append("\n");
            headers.forEach(field ->
                    tasksCsv.append(String.format("%s,", ((Map<String, Object>) task).get(field))));
            tasksCsv.deleteCharAt(tasksCsv.length() - 1);
        });
        return tasksCsv.toString();

    }
}
