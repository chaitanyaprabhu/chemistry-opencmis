package org.apache.chemistry.opencmis.commons.api.server;

import java.util.List;

import org.apache.chemistry.opencmis.commons.api.Acl;
import org.apache.chemistry.opencmis.commons.api.AclService;
import org.apache.chemistry.opencmis.commons.api.ContentStream;
import org.apache.chemistry.opencmis.commons.api.DiscoveryService;
import org.apache.chemistry.opencmis.commons.api.ExtensionsData;
import org.apache.chemistry.opencmis.commons.api.MultiFilingService;
import org.apache.chemistry.opencmis.commons.api.NavigationService;
import org.apache.chemistry.opencmis.commons.api.ObjectService;
import org.apache.chemistry.opencmis.commons.api.PolicyService;
import org.apache.chemistry.opencmis.commons.api.Properties;
import org.apache.chemistry.opencmis.commons.api.RelationshipService;
import org.apache.chemistry.opencmis.commons.api.RepositoryService;
import org.apache.chemistry.opencmis.commons.api.VersioningService;
import org.apache.chemistry.opencmis.commons.enums.AclPropagation;
import org.apache.chemistry.opencmis.commons.enums.VersioningState;

/**
 * OpenCMIS server interface.
 * 
 * <p>
 * <em>
 * See CMIS 1.0 specification for details on the operations, parameters,
 * exceptions and the domain model.
 * </em>
 * </p>
 * 
 * <p>
 * This interface adds a few more operations to the operation set defined by
 * CMIS to address binding specific requirements.
 * </p>
 */
public interface CmisService extends RepositoryService, NavigationService, ObjectService, VersioningService,
        DiscoveryService, MultiFilingService, RelationshipService, AclService, PolicyService {

    /**
     * Creates a new document, folder or policy.
     * 
     * The property "cmis:objectTypeId" defines the type and implicitly the base
     * type.
     * 
     * @param repositoryId
     *            the identifier for the repository
     * @param properties
     *            the property values that MUST be applied to the newly created
     *            object
     * @param folderId
     *            <em>(optional)</em> if specified, the identifier for the
     *            folder that MUST be the parent folder for the newly created
     *            object
     * @param contentStream
     *            <em>(optional)</em> if the object to create is a document
     *            object, the content stream that MUST be stored for the newly
     *            created document object
     * @param versioningState
     *            <em>(optional)</em> if the object to create is a document
     *            object, it specifies what the versioning state of the newly
     *            created object MUST be (default is
     *            {@link VersioningState#MAJOR})
     * @param policies
     *            <em>(optional)</em> a list of policy IDs that MUST be applied
     *            to the newly created object
     * @param addAces
     *            <em>(optional)</em> a list of ACEs that MUST be added to the
     *            newly created object, either using the ACL from
     *            <code>folderId</code> if specified, or being applied if no
     *            <code>folderId</code> is specified
     * @param removeAces
     *            <em>(optional)</em> a list of ACEs that MUST be removed from
     *            the newly created object, either using the ACL from
     *            <code>folderId</code> if specified, or being ignored if no
     *            <code>folderId</code> is specified
     */
    String create(String repositoryId, Properties properties, String folderId, ContentStream contentStream,
            VersioningState versioningState, List<String> policies, ExtensionsData extension);

    /**
     * Deletes an object or cancels a check out.
     * 
     * For the Web Services binding this is always an object deletion. For the
     * AtomPub it depends on the referenced object. If it is a checked out
     * document then the check out must be canceled. If the object is not a
     * checked out document then the object must be deleted.
     * 
     * @param repositoryId
     *            the identifier for the repository
     * @param objectId
     *            the identifier for the object
     * @param allVersions
     *            <em>(optional)</em> If <code>true</code> then delete all
     *            versions of the document, otherwise delete only the document
     *            object specified (default is <code>true</code>)
     */
    void deleteObjectOrCancelCheckOut(String repositoryId, String objectId, Boolean allVersions,
            ExtensionsData extension);

    /**
     * Applies a new ACL to an object.
     * 
     * Since it is not possible to transmit an "add ACL" and a "remove ACL" via
     * AtomPub, the merging has to be done the client side. The ACEs provided
     * here is supposed to the new complete ACL.
     * 
     * @param repositoryId
     *            the identifier for the repository
     * @param objectId
     *            the identifier for the object
     * @param aces
     *            the ACEs that should replace the current ACL of the object
     * @param aclPropagation
     *            <em>(optional)</em> specifies how ACEs should be handled
     *            (default is {@link AclPropagation#REPOSITORYDETERMINED})
     */
    Acl applyAcl(String repositoryId, String objectId, Acl aces, AclPropagation aclPropagation);

    /**
     * Returns the {@link ObjectInfo} of the given object id or
     * <code>null</code> if no object info exists.
     * 
     * Only AtomPub requests will require object infos.
     * 
     * @param repositoryId
     *            the identifier for the repository
     * @param objectId
     *            the identifier for the object
     */
    ObjectInfo getObjectInfo(String repositoryId, String objectId);

    /**
     * Signals that this object will not be used anymore and resources can
     * released.
     */
    void close();
}