package com.example.annotation_2_2;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@SaveTo(path="C:\\JavaCourse\\javapro_03072022\\homeworks\\" +
        "annotation_2_2\\src\\com\\example\\annotation_2_2\\file.txt")
class TextContainer{
    String text="Слава Україні! " +
            "Героям слава";

    @Saver
    public void save(String path) throws IOException {
        try (FileWriter w = new FileWriter(path)) {
            w.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class Main {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        TextContainer textContainer=new TextContainer();
        Class<?> cls=textContainer.getClass();
        String path="";
        if(cls.isAnnotationPresent(SaveTo.class)){
            SaveTo saveTo=cls.getAnnotation(SaveTo.class);
            path= saveTo.path();
        }
        System.out.println("Path= "+path);
        Method[] methods=cls.getDeclaredMethods();

        for(Method md: methods){
            if(md.isAnnotationPresent(Saver.class)){
                md.invoke(textContainer,path);
            }
        }
    }
}
