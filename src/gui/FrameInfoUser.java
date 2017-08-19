package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.layout.GridData;

public class FrameInfoUser {
	
	private String fila;
	private Usuario user;
	private Shell parent;
	
	public void showFila(Usuario user) {
		this.user = user;
		
		show();
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public void show() {


		
		Shell shell = new Shell(Display.getCurrent());



		shell.setSize(343, 228);
		shell.setText("Usuario");
		shell.setLayout(new GridLayout(1, false));
		
		Group grpInfo = new Group(shell, SWT.NONE);
		grpInfo.setText("Info");
		grpInfo.setLayout(new GridLayout(2, false));
		GridData gd_grpInfo = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_grpInfo.heightHint = 159;
		gd_grpInfo.widthHint = 313;
		grpInfo.setLayoutData(gd_grpInfo);
		
		Label lblNombre = new Label(grpInfo, SWT.NONE);
		lblNombre.setText("Nombre: ");
		
		Label label = new Label(grpInfo, SWT.NONE);
		label.setText(user.getNombre());
		
		Label lblApellido = new Label(grpInfo, SWT.NONE);
		lblApellido.setText("Apellido: ");
		
		Label lblNewLabel = new Label(grpInfo, SWT.NONE);
		lblNewLabel.setText(user.getApellido());
		
		shell.open();
		
		while (!shell.isDisposed()) {
		    if (!Display.getCurrent().readAndDispatch())
		     {
		    	Display.getCurrent().sleep();
		     }
		}
	}

}
