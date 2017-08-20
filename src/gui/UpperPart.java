package gui;

import com.sun.deploy.uitoolkit.impl.fx.ui.FXMessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.*;

import java.util.List;

import db.DBController;
import sun.plugin2.message.Message;

public class UpperPart {

    private Button btn;
    private Label l;
    private Button add;
    private Table table;
    private Menu menuTable;
    private Shell parent1;

    public UpperPart() {
        MainFrame mn = new MainFrame();
        parent1 = mn.getShellParent();
    }

    /**
     * @wbp.parser.entryPoint
     */
    public void show(Composite parent) {


        table = new Table(parent, SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION);
        table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        table.setLinesVisible(true);
        table.setHeaderVisible(true);

        String[] titles = {"id", "Nombre", "Apellido", "Edad"};

        TableColumn column1 = new TableColumn(table, SWT.NONE);
        column1.setText(titles[0]);
        column1.setWidth(50);
        TableColumn column2 = new TableColumn(table, SWT.NONE);
        column2.setText(titles[1]);
        column2.setWidth(200);
        TableColumn column3 = new TableColumn(table, SWT.NONE);
        column3.setText(titles[2]);
        column3.setWidth(200);
        TableColumn column4 = new TableColumn(table, SWT.NONE);
        column4.setText(titles[3]);
        column4.setWidth(304);


        updateTable();

        add = new Button(parent, SWT.PUSH);
        add.setText("Add user");

        add.addListener(SWT.Selection, new Listener() {
            public void handleEvent(Event event) {

                FrameFormUser fmu = new FrameFormUser();
                MainFrame mn = new MainFrame();
                fmu.show(mn.getShellParent());
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
                    info.showFila((Usuario) itemsSelected[0].getData(), parent1);
                    parent1.setEnabled(true);
                    table.deselectAll();
                }

            }
        });

        eliminar.addListener(SWT.Selection, new Listener() {
            @Override
            public void handleEvent(Event event) {
                TableItem[] itemsSelected = table.getSelection();
                if (itemsSelected.length != 0) {
                    MessageBox dialog = new MessageBox(parent1, SWT.ICON_WARNING | SWT.OK | SWT.CANCEL);
                    dialog.setText("Elimiar usuario");
                    dialog.setMessage("Quiere eliminar el usuario: " + itemsSelected[0].getText(1) + "?");
                    int res = dialog.open();
                    if (res == SWT.OK) {
                        try {
                            DBController db = new DBController();
                            db.deleteUser((Usuario) itemsSelected[0].getData());
                            updateTable();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }


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

        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseDown(MouseEvent mouseEvent) {

                if (table.getSelection().length == 0) {
                    System.out.println("deselect");
                }
            }

            @Override
            public void mouseUp(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseDoubleClick(MouseEvent mouseEvent) {
                TableItem[] itemsSelected = table.getSelection();

                if (itemsSelected.length != 0) {
                    FrameInfoUser info = new FrameInfoUser();
                    info.showFila((Usuario) itemsSelected[0].getData(), parent1);
                    parent1.setEnabled(true);
                    table.deselectAll();
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
