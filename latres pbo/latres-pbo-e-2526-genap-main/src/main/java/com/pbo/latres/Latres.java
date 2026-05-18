/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.pbo.latres;

import com.pbo.latres.dto.InsertTodoDTO;
import com.pbo.latres.model.FakeTodoRepository; //ga kepake
import com.pbo.latres.model.TodoRepository;
import com.pbo.latres.model.TodoTask;
import com.pbo.latres.view.TodoView;
import com.pbo.latres.config.Koneksi;
import com.pbo.latres.model.DbToDoRepository;

/**
 *
 * @author farhannivta
 */
public class Latres {

    public static void main(String[] args) {
        Koneksi.getKoneksi();
        TodoRepository repository =
            new DbToDoRepository();

        TodoView view = new TodoView();

        Runnable refreshTable = () -> {
            view.showTodos(
                repository.getAll()
            );
        };

        view.onAdd(e -> {

            String title =
                view.getTitleInput();

            String status =
                view.getStatusInput();

            if (title.isEmpty()) {

                view.showMessage(
                    "Task tidak boleh kosong"
                );

                return;
            }

            repository.insert(
                new InsertTodoDTO(
                    title,
                    status
                )
            );

            refreshTable.run();

            view.clearForm();
        });

        view.onUpdate(e -> {

            int selectedId =
                view.getSelectedTodoId();

            if (selectedId == -1) {

                view.showMessage(
                    "Pilih data terlebih dahulu"
                );

                return;
            }

            TodoTask task = new TodoTask(
                selectedId,
                view.getTitleInput(),
                view.getStatusInput()
            );

            repository.update(task);

            refreshTable.run();

            view.clearForm();
        });

        view.onDelete(e -> {

            int selectedId =
                view.getSelectedTodoId();

            if (selectedId == -1) {

                view.showMessage(
                    "Pilih data terlebih dahulu"
                );

                return;
            }

            repository.deleteById(
                selectedId
            );

            refreshTable.run();

            view.clearForm();
        });

        view.onClear(e -> {
            view.clearForm();
        });

        view.onTableSelect(e -> {

            int selectedId =
                view.getSelectedTodoId();

            if (selectedId == -1) {
                return;
            }

            TodoTask task =
                repository.getById(selectedId);

            if (task != null) {
                view.setForm(task);
            }
        });

        refreshTable.run();
    }
}
