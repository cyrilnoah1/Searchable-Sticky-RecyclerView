package com.example.rapid.stickyrecyclerdemo2;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.zakariya.stickyheaders.SectioningAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * REcyclerAdapter
 */

public class RecyclerAdapter extends SectioningAdapter {

    final String TAG = "RecyclerAdapter";

    private List<Section> mSections;
    private List<Contact> mSearchFilter;
    RecyclerAdapter.ResetAdapter mResetAdapter;
    private MainActivity mMainActivity;

    public RecyclerAdapter(List<Contact> data, ResetAdapter resetAdapter, MainActivity mainActivity
    ) {
        //Setting the sections based on people
        mMainActivity = mainActivity;
        mResetAdapter = resetAdapter;
        mSections = new ArrayList<>();
        mSearchFilter = new ArrayList<>();
        mSearchFilter.addAll(data);
        setContacts(mSearchFilter);
    }

    public class ItemViewHolder extends SectioningAdapter.ItemViewHolder {
        TextView contactNameTextView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            contactNameTextView = (TextView) itemView.findViewById(R.id.tv_recycler_view_contact);
        }
    }

    public class HeaderViewHolder extends SectioningAdapter.HeaderViewHolder {
        TextView titleTextView;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            titleTextView = (TextView) itemView.findViewById(R.id.titleTextView);
        }
    }

    @Override
    public ItemViewHolder onCreateItemViewHolder(ViewGroup parent, int itemType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.list_item_addressbook_contact, parent, false);
        return new ItemViewHolder(v);
    }

    @Override
    public HeaderViewHolder onCreateHeaderViewHolder(ViewGroup parent, int headerType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.list_item_addressbook_header, parent, false);
        return new HeaderViewHolder(v);
    }

    @Override
    public void onBindItemViewHolder(SectioningAdapter.ItemViewHolder viewHolder,
                                     int sectionIndex, int itemIndex, int itemType) {
        Section s = mSections.get(sectionIndex);
        ItemViewHolder ivh = (ItemViewHolder) viewHolder;
        Contact contact = s.contacts.get(itemIndex);
        ivh.contactNameTextView.setText(contact.getName());
    }

    @Override
    public void onBindHeaderViewHolder(SectioningAdapter.HeaderViewHolder viewHolder,
                                       int sectionIndex, int headerType) {
        Section s = mSections.get(sectionIndex);
        HeaderViewHolder hvh = (HeaderViewHolder) viewHolder;
        if (s != null)
            hvh.titleTextView.setText(s.alpha);
    }

    @Override
    public int getNumberOfSections() {
        return mSections.size();
    }

    @Override
    public int getNumberOfItemsInSection(int sectionIndex) {
        if (mSections.get(sectionIndex) == null) {
            return 0;
        }
        return mSections.get(sectionIndex).contacts.size();
    }

    @Override
    public boolean doesSectionHaveHeader(int sectionIndex) {
        return true;
    }

    /**
     * Section class to filter the contacts based on their alphabetical section.
     */
    private class Section {
        String alpha;
        ArrayList<Contact> contacts = new ArrayList<>();
    }

    /**
     * To provide section names based on the contacts.
     *
     * @param contacts .
     */
    private void setContacts(List<Contact> contacts) {
        if (mSections != null) {
            mSections.clear();
        }
        // Sorting contacts into buckets based on
        // the first letter of Contact Name.
        char alpha = 0;
        Section currentSection = null;
        for (Contact contact : contacts) {
            if (contact.getName().charAt(0) != alpha) {
                if (currentSection != null) {
                    mSections.add(currentSection);
                }

                currentSection = new Section();
                alpha = contact.getName().charAt(0);
                currentSection.alpha = String.valueOf(alpha);
            }

            if (currentSection != null) {
                currentSection.contacts.add(contact);
            }
        }

        mSections.add(currentSection);
    }

    /**
     * To load data into the RecyclerView based on the search filter.
     *
     * @param name        .
     * @param contactData .
     */
    public void contactFilter(final String name, final List<Contact> contactData) {
        mMainActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mSearchFilter.clear();

                if (TextUtils.isEmpty(name)) {

                    mSearchFilter.addAll(contactData);
                } else {
                    for (Contact contact : contactData) {

                        if (contact.getName().toLowerCase()
                                .contains(name.toLowerCase())) {
                            mSearchFilter.add(contact);
                        }
                    }
                }
                mResetAdapter.resetAdapter(mSearchFilter);
            }
        });
    }

    /**
     * To reload Recycler's data based on the search filter.
     */
    public interface ResetAdapter {
        void resetAdapter(List<Contact> contacts);
    }
}
