package com.kevinshine.beyondmediaplayer.widget.filepicker;

import android.net.Uri;
import android.support.v4.content.Loader;
import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * An interface for the methods required to handle backend-specific stuff.
 */
public interface LogicHandler<T> {

    int VIEWTYPE_HEADER = 0;
    int VIEWTYPE_DIR = 1;
    int VIEWTYPE_CHECKABLE = 2;

    /**
     * Return true if the path is a directory and not a file.
     *
     * @param path
     */
    boolean isDir(final T path);

    /**
     * @param path
     * @return filename of path
     */
    String getName(final T path);

    /**
     * Convert the path to a URI for the return intent
     *
     * @param path
     * @return a Uri
     */
    Uri toUri(final T path);

    /**
     * Return the path to the parent directory. Should return the root if
     * from is root.
     *
     * @param from
     */
    T getParent(final T from);

    /**
     * @param path
     * @return the full path to the file
     */
    String getFullPath(final T path);

    /**
     * Convert the path to the type used.
     *
     * @param path
     */
    T getPath(final String path);

    /**
     * Get the root path (lowest allowed).
     */
    T getRoot();

    /**
     * Get a loader that lists the files in the current path,
     * and monitors changes.
     */
    Loader<SortedList<T>> getLoader();

    /**
     * Bind the header ".." which goes to parent folder.
     *
     * @param viewHolder
     */
    void onBindHeaderViewHolder(AbstractFilePickerFragment<T>.HeaderViewHolder viewHolder);

    /**
     * Header is subtracted from the position
     *
     * @param parent
     * @param viewType
     * @return a view holder for a file or directory
     */
    RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType);

    /**
     * @param viewHolder to bind data from either a file or directory
     * @param position   0 - n, where the header has been subtracted
     * @param data
     */
    void onBindViewHolder(AbstractFilePickerFragment<T>.DirViewHolder viewHolder, int position, T data);

    /**
     * @param position 0 - n, where the header has been subtracted
     * @param data
     * @return an integer greater than 0
     */
    int getItemViewType(int position, T data);
}
