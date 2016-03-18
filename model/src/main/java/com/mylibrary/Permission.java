package com.mylibrary;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "permission")
public class Permission implements Serializable {
    @Id
    @SequenceGenerator(name = "permission_seq", sequenceName = "permission_seq_id", allocationSize = 0)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "permission_seq")
    @Column(name = "id")
    private int id;
    @Column(name = "can_read")
    private boolean canRead;
    @Column(name = "can_edit")
    private boolean canEdit;
    @Column(name = "can_delete")
    private boolean canDelete;

    public Permission() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isCanRead() {
        return canRead;
    }

    public void setCanRead(boolean canRead) {
        this.canRead = canRead;
    }

    public boolean isCanEdit() {
        return canEdit;
    }

    public void setCanEdit(boolean canEdit) {
        this.canEdit = canEdit;
    }

    public boolean isCanDelete() {
        return canDelete;
    }

    public void setCanDelete(boolean canDelete) {
        this.canDelete = canDelete;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Permission permission = (Permission) o;

        if (id != permission.id) return false;
        if (canRead != permission.canRead) return false;
        if (canEdit != permission.canEdit) return false;
        return canDelete == permission.canDelete;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (canRead ? 1 : 0);
        result = 31 * result + (canEdit ? 1 : 0);
        result = 31 * result + (canDelete ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", canRead=" + canRead +
                ", canEdit=" + canEdit +
                ", canDelete=" + canDelete +
                '}';
    }
}
