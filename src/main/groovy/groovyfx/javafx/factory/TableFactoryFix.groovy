/*
 * Copyright 2011-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package groovyfx.javafx.factory

import groovyx.javafx.event.GroovyCallback
import groovyx.javafx.factory.AbstractNodeFactory
import groovyx.javafx.factory.ConverterPropertyValueFactory
import groovyx.javafx.factory.EditingCallback
import groovyx.javafx.factory.EnumEditingCallback
import javafx.beans.value.ChangeListener
import javafx.beans.value.ObservableValue
import javafx.scene.control.SelectionMode
import javafx.scene.control.TableColumn
import javafx.scene.control.TableView
import javafx.scene.control.cell.PropertyValueFactory
import org.codehaus.groovy.runtime.InvokerHelper

/**
 *
 * @author jimclarke
 */

// Generic enum choicebox editor.

// creates a mapping between two different types of ObservableValues
// For example String and Date

class TableFactoryFix extends AbstractNodeFactory {
    private static EditingCallback defaultCellFactory = new EditingCallback();
    private static EnumEditingCallback enumCellFactory = new EnumEditingCallback();
    
    public TableFactoryFix(Class beanClass) {
        super(beanClass)
    }
    
    public Object newInstance(FactoryBuilderSupport builder, Object name, Object value, Map attributes) throws InstantiationException, IllegalAccessException {
        Object result = super.newInstance(builder, name, value, attributes);
        if(TableColumn.isAssignableFrom(beanClass) && value != null) {
            result.text = value.toString()
        }
        result
    }
    
    boolean onHandleNodeAttributes(FactoryBuilderSupport builder, Object node, Map attributes) {
        if(node instanceof TableView) {
            
            // Handle selection attributes
            def selectionMode= attributes.remove("selectionMode");
            if(selectionMode != null) {
                
                def mode = selectionMode instanceof SelectionMode ?
                            selectionMode : SelectionMode.valueOf(selectionMode.toUpperCase());
                node.getSelectionModel().selectionMode = mode;
            }
            def cellSelectionEnabled = attributes.remove("cellSelectionEnabled");
            if(cellSelectionEnabled != null) {
                node.getSelectionModel().cellSelectionEnabled = cellSelectionEnabled;
            }
            def selectedIndex = attributes.remove("selectedIndex");
            if(selectedIndex != null) {
                node.getSelectionModel().selectedIndex = selectedIndex;
            }
            def selectedItem = attributes.remove("selectedItem");
            if(selectedItem != null) {
                node.getSelectionModel().selectedItem = selectedItem;
            }
        }else { // TableColumn
            def converter = attributes.remove("converter");
            //TODO what how to do conversion like date-String etc.
            def type = attributes.remove("type");
            def property = attributes.remove("property");
            if(property != null) {
                if(converter != null) {
                    node.cellValueFactory  = new ConverterPropertyValueFactory(property, converter)
                }else {
                    node.cellValueFactory = new PropertyValueFactory(property);
                }
            }
            
            
            // class type for the field, default is String
            
            def editable = attributes.get("editable"); // leave for superclass processing
            def hasCellFactory = attributes.containsKey("cellFactory"); // leave for superclass processing
            if(editable && !hasCellFactory) {
                def cellFactory = null;
                if(type != null && Enum.class.isAssignableFrom(type)) {
                    cellFactory = new EnumEditingCallback(type);
                }
                /** TODO if necessary build a map of cellFactories.
                if(type != null)
                    cellFactory = cellFactories.get(type);
                **/
                if(cellFactory == null)
                    cellFactory = defaultCellFactory;
                node.setCellFactory(cellFactory);
            }
        }

        return super.onHandleNodeAttributes(builder, node, attributes);
    }

    public void setChild( FactoryBuilderSupport builder, Object parent, Object child ) {
        if((parent instanceof TableView || parent instanceof TableColumn) && child instanceof TableColumn) {
            parent.columns.add(child);
        }else if(child instanceof GroovyCallback) {
            if(parent instanceof TableView && child.property == "onSelect") {
                   parent.selectionModel.selectedItemProperty().addListener(new ChangeListener() {
                        public void changed(final ObservableValue observable, final Object oldValue, final Object newValue) {
                            builder.defer({child.closure.call(parent, oldValue, newValue);});
                        }
                    });      
            }else if(parent instanceof TableColumn ) {
                InvokerHelper.setProperty(parent, child.property, child);  // Fix node to parent
            }
        }else {
            super.setChild(builder, parent, child)
        }
    }
}
