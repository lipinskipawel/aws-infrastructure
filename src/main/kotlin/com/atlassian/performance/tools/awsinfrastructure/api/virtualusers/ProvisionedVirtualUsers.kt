package com.atlassian.performance.tools.awsinfrastructure.api.virtualusers

import com.atlassian.performance.tools.aws.api.Resource
import com.atlassian.performance.tools.aws.api.UnallocatedResource
import com.atlassian.performance.tools.awsinfrastructure.api.network.access.AccessRequester
import com.atlassian.performance.tools.awsinfrastructure.api.network.access.NoAccessRequester
import com.atlassian.performance.tools.infrastructure.api.virtualusers.VirtualUsers

class ProvisionedVirtualUsers<out T : VirtualUsers> private constructor(
    val virtualUsers: T,
    val resource: Resource,
    val accessRequester: AccessRequester
) {
    object Defaults {
        val resource: Resource = UnallocatedResource()
        val accessRequester: AccessRequester = NoAccessRequester()
    }

    @Deprecated("Use ProvisionedVirtualUsers.Builder instead.")
    constructor(
        virtualUsers: T,
        resource: Resource
    ) : this(
        virtualUsers = virtualUsers,
        resource = resource,
        accessRequester = Defaults.accessRequester
    )

    override fun toString(): String {
        return "ProvisionedVirtualUsers(virtualUsers=$virtualUsers, resource=$resource)"
    }

    class Builder<out T : VirtualUsers>(
        private val virtualUsers: T
    ) {
        private var resource: Resource = Defaults.resource
        private var accessRequester: AccessRequester = Defaults.accessRequester

        fun resource(resource: Resource) = apply { this.resource = resource }
        fun accessRequester(accessRequester: AccessRequester) = apply { this.accessRequester = accessRequester }

        fun build() = ProvisionedVirtualUsers(
            virtualUsers = virtualUsers,
            resource = resource,
            accessRequester = accessRequester
        )
    }
}