package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;


public class FrameLogin {
    private Usuario user;
    private Label usuario, contrasena;
    private Text campoUsuario, campoContrasena;
    private Button login;

    public FrameLogin(){

    }

    public void show(){
        Display display = new Display();
        Shell shell = new Shell(display);
        shell.setText("Login");
        shell.setMinimumSize(500,400);
        shell.setSize(500,400);

        GridLayout grid = new GridLayout(2,false);
        shell.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,true));
        Composite comp = new Composite(shell, SWT.NONE);

        shell.setLayout(grid);
        comp.setLayout(grid);

        usuario = new Label(comp, SWT.NONE);
        usuario.setText("Usuario: ");

        campoUsuario = new Text(comp, SWT.BORDER);

        contrasena = new Label(comp, SWT.NONE);
        contrasena.setText("Contraseña: ");

        campoContrasena = new Text(comp, SWT.BORDER);

        login = new Button(comp, SWT.PUSH);
        login.setText("Login");
        GridData gd = new GridData(SWT.RIGHT, SWT.NONE, true,false);
        gd.horizontalSpan = 2;
        login.setLayoutData(gd);

        comp.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true,true));






        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
    }


}
