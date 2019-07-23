package com.extremesolution.commonservice.repository.base;

import com.extremesolution.commonservice.general.util.exception.BusinessException;
import com.extremesolution.commonservice.model.base.AbstractEntity;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.firebase.cloud.FirestoreClient;

public abstract class AbstractRepository<T extends AbstractEntity> {
	
	public CollectionReference collectionReference;

	public void setCollectionReference(String collectionName) {
		this.collectionReference = FirestoreClient.getFirestore().collection(collectionName);;
	}

	public String save(T object) {
		// Add document data after generating an id.
		DocumentReference addedDocRef = collectionReference.document();
		String generatedId = addedDocRef.getId();
		if(addedDocRef.set(object).isCancelled())
			throw new BusinessException("General00003");
		return generatedId;
	}
	
	public void update(String id, T object){
		if(collectionReference.document(id).set(object).isCancelled())
			throw new BusinessException("General00004");
	}
	
	public DocumentSnapshot get(String id) {
		DocumentReference docRef = collectionReference.document(id);
		// asynchronously retrieve the document
		ApiFuture<DocumentSnapshot> future = docRef.get();
		// block on response
		DocumentSnapshot document;
		try {
			document = future.get();
			if (!document.exists()) 
				throw new BusinessException("General00002",new Object[] {id});
			
			return document;
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("");
		}
	}
	
	public Boolean delete(String id){
		if(collectionReference.document(id).delete().isCancelled())
			return false;
		return true;
	}
	
}
