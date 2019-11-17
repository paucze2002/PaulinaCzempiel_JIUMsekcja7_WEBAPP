/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathapplication.model;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * Class which defines new type of annotation. Shows mechanism.
 * 
 * @author Paulina Czempiel
 * @version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface TestAnnotation {
    /**
     * Author's name
     * @return annotation
     */
    String authorFirstName();
    /**
     * Author's surname
     * @return annotation
     */
    String authorLastName();
    /**
     * Annotation desciption
     * @return annotation
     */
    String authorBrief();
}
