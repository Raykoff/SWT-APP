package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;
import org.eclipse.wb.swt.SWTResourceManager;

public class MainFrame {

	static Shell parent;

	public MainFrame() {

	}

	/**
	 * @wbp.parser.entryPoint
	 */
	public void show() {

		Display display = new Display();
		Shell shlMiPrograma = new Shell(display);
		shlMiPrograma.setText("Mi Programa");
		shlMiPrograma.setImage(SWTResourceManager.getImage("C:\\Users\\Raykov\\workspace-spring\\Pantalla2\\images\\icon-shell.png"));

		shlMiPrograma.setLayout(new GridLayout(1, false));
		shlMiPrograma.setSize(800,500);
		shlMiPrograma.setMinimumSize(800,500);

		parent = shlMiPrograma.getShell();


		// CONTENEDOR GROUP ARRIBA
		Group upperGroup = new Group(shlMiPrograma, SWT.NONE);
		GridLayout gridGroup = new GridLayout(1, false);
		upperGroup.setLayout(gridGroup);
		upperGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		upperGroup.setText("Busqueda");

		UpperPart up = new UpperPart();
		up.show(upperGroup);

		
		Menu menu = new Menu(shlMiPrograma, SWT.BAR);
		shlMiPrograma.setMenuBar(menu);
		
		MenuItem mntmNewItem = new MenuItem(menu, SWT.CASCADE);
		mntmNewItem.setText("New Item");
		
		Menu menu_1 = new Menu(mntmNewItem);
		mntmNewItem.setMenu(menu_1);
		
		MenuItem mntmNewItem_3 = new MenuItem(menu_1, SWT.NONE);
		mntmNewItem_3.setText("New Item");
		
		MenuItem mntmNewItem_4 = new MenuItem(menu_1, SWT.NONE);
		mntmNewItem_4.setText("New Item");
		
		MenuItem mntmNewItem_1 = new MenuItem(menu, SWT.NONE);
		mntmNewItem_1.setText("New Item");
		
		MenuItem mntmNewItem_2 = new MenuItem(menu, SWT.NONE);
		mntmNewItem_2.setText("New Item");

//		 shell.pack();
		shlMiPrograma.open();
		while (!shlMiPrograma.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	public Shell getShellParent(){
		return parent;
	}
}
