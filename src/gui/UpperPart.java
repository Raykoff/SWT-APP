package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.*;
import java.util.List;

import db.DBController;

public class UpperPart {

	private Button btn;
	private Label l;
	private Button add;
	private Table table;
	private Menu menuTable;

	public UpperPart() {
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	public void show(Composite parent) {
		table = new Table(parent, SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		table.setLinesVisible(true);
		table.setHeaderVisible(true);

		String[] titles = { "id", "Nombre", "Apellido", "Edad" };

		for (int loopIndex = 0; loopIndex < titles.length; loopIndex++) {
			TableColumn column = new TableColumn(table, SWT.NONE);
			column.setText(titles[loopIndex]);
		}

		updateTable();

		for (int loopIndex = 0; loopIndex < titles.length; loopIndex++) {
			table.getColumn(loopIndex).pack();
		}

		add = new Button(parent, SWT.PUSH);
		add.setText("Add user");

		add.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {

				FrameFormUser fmu = new FrameFormUser();
				fmu.show();

				MainFrame mn = new MainFrame();
				mn.getShellParent().setEnabled(true);

				Usuario user = fmu.getUsuario();

				if (user != null) {

					DBController db = new DBController();
					db.addUsuario(user);

					updateTable();
				}
			}
		});

		// MENU DE LA TABLA

		this.menuTable = new Menu(table);
		table.setMenu(menuTable);

		MenuItem ver = new MenuItem(menuTable, SWT.NONE);
		ver.setText("Ver usuario");

		MenuItem eliminar = new MenuItem(menuTable, SWT.NONE);
		eliminar.setText("Eliminar usuario");

		ver.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				TableItem[] itemsSelected = table.getSelection();

				if (itemsSelected.length != 0) {
					FrameInfoUser info = new FrameInfoUser();
					info.showFila((Usuario) itemsSelected[0].getData());
				}

			}
		});
		
		eliminar.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				TableItem[] itemsSelected = table.getSelection();
				if (itemsSelected.length != 0) {
					DBController db = new DBController();
					db.deleteUser((Usuario)itemsSelected[0].getData());
					updateTable();
				}
				
			}	
		});

		table.addListener(SWT.MenuDetect, new Listener() {
			@Override
			public void handleEvent(Event event) {
				if (table.getSelectionCount() <= 0) {
					event.doit = false;
				}
			}
		});

		// FIN MENU DE LA TABLA

	}

	public void updateTable() {

		table.removeAll();
		DBController db = new DBController();

		List<Usuario> users = db.getUsuarios();

		for (int i = 0; i < users.size(); i++) {
			TableItem item = new TableItem(table, SWT.NONE);
			item.setData(users.get(i));
			item.setText(0, String.valueOf(users.get(i).getId()));
			item.setText(1, users.get(i).getNombre());
			item.setText(2, users.get(i).getApellido());
			item.setText(3, String.valueOf(users.get(i).getEdad()));

		}
	}

}
