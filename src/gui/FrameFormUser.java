package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;

public class FrameFormUser {

    private Usuario user;
    private Label labelUser, labelApellido, labelError;
    private Text campoNombre, campoApellido, campoEdad;
    private Button anadir;
    private Label lblNewLabel;
    private Text text;
    Shell parent;

    // public FrameFormUser(Usuario user) {
    // this.user = user;
    // }
    //

    /**
     */
    public void show(Shell parent) {
        Shell shell = new Shell(parent);

        parent.setEnabled(false);

        shell.setText("Add user");
        shell.setSize(292, 192);
        shell.setLayout(new GridLayout(2, false));

        labelUser = new Label(shell, SWT.NONE);
        labelUser.setText("Nombre: ");

        campoNombre = new Text(shell, SWT.BORDER);
        campoNombre.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

        labelApellido = new Label(shell, SWT.NONE);
        labelApellido.setText("Apellido: ");

        campoApellido = new Text(shell, SWT.BORDER);
        campoApellido.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

        lblNewLabel = new Label(shell, SWT.NONE);
        lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
        lblNewLabel.setText("Edad: ");

        campoEdad = new Text(shell, SWT.BORDER);
        campoEdad.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

        campoEdad.addListener(SWT.Verify, new Listener() {
            public void handleEvent(Event e) {
                String string = e.text;
                char[] chars = new char[string.length()];
                string.getChars(0, chars.length, chars, 0);
                for (int i = 0; i < chars.length; i++) {
                    if (!('0' <= chars[i] && chars[i] <= '9')) {
                        labelError.setText("Debe ser un numero");
                        labelError.setVisible(true);
                        e.doit = false;
                        return;
                    }
                }
            }
        });
        new Label(shell, SWT.NONE);

        labelError = new Label(shell, SWT.NONE);
        labelError.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
        labelError.setText("Introduce todos los campos");
        labelError.setVisible(false);
        new Label(shell, SWT.NONE);

        anadir = new Button(shell, SWT.PUSH);
        GridData gd_anadir = new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1);
        gd_anadir.widthHint = 77;
        anadir.setLayoutData(gd_anadir);
        anadir.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
            }
        });
        anadir.setText("Add");

        anadir.addListener(SWT.Selection, new Listener() {
            public void handleEvent(Event event) {

                if (campoEdad.getText() != "") {
                    Usuario u = new Usuario(campoNombre.getText(), campoApellido.getText(),
                            Integer.parseInt(campoEdad.getText()));

                    if (u.isSet()) {
                        user = u;
                        // System.out.println(event.text);
                        shell.close();
                    }
                } else {

                    labelError.setVisible(true);
                }

            }

        });


        shell.open();




        while (!shell.isDisposed()) {
            if (!Display.getCurrent().readAndDispatch()) {
                Display.getCurrent().sleep();
            }
        }
    }

    public Usuario getUsuario() {
        return this.user;
    }

}
