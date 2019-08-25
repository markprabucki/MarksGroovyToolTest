import groovy.swing.SwingBuilder  
import groovy.beans.Bindable  
import static javax.swing.JFrame.EXIT_ON_CLOSE  
import java.awt.*
 
@Bindable
class args {  
    String arg1, arg2, arg3
    String toString() { "args[arg1=$arg1,arg2=$arg2,arg3=$arg3]" }
}
  
def args = new args(arg1: 'arg1', arg2: 'arg2', arg3: 'arg3')
  
def swingBuilder = new SwingBuilder()
swingBuilder.edt {  
    lookAndFeel 'nimbus'  
    frame(title: 'Marks Groovy Util', size: [350, 230], 
            show: true, locationRelativeTo: null, 
            defaultCloseOperation: EXIT_ON_CLOSE) { 
        borderLayout(vgap: 5)
        
        panel(constraints: BorderLayout.CENTER, 
                border: compoundBorder([emptyBorder(10), titledBorder('Enter Args:')])) {
            tableLayout {
                tr {
                    td {
                        label 'arg1:'  // text property is default, so it is implicit.
                    }
                    td {
                        textField args.arg1, id: 'arg1Field', columns: 20
                    }
                }
                tr {
                    td {
                        label 'arg2:'
                    }
                    td {
                        textField id: 'arg2Field', columns: 5, text: args.arg2
                    }
                }
                tr {
                    td {
                        label 'arg3:'
                    }
                    td {
                        textField id: 'arg3Field', columns: 20, args.arg3
                    }
                }
            }
            
        }
        
        panel(constraints: BorderLayout.SOUTH) {
            button text: 'Save', actionPerformed: {
                println args
            }
        }
        
        // Binding of textfield's to address object.
        bean args, 
            arg1: bind { arg1Field.text }, 
            arg2: bind { arg2Field.text }, 
            arg3: bind { arg3Field.text }
    }  
}