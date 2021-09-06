package com.iislex.mvvmgithubsample.network.model

import com.iislex.mvvmgithubsample.domain.model.User
import com.iislex.mvvmgithubsample.domain.util.DomainMapper

class UserDtoMapper: DomainMapper<UserDto, User> {
    override fun mapToDomainModel(model: UserDto): User {
        return User(
            avatar_url = model.avatarUrl,
            bio = model.bio,
            blog = model.blog,
            company = model.company,
            created_at = model.createdAt,
            email = model.email,
            events_url = model.eventsUrl,
            followers = model.followers,
            followers_url = model.followersUrl,
            following = model.following,
            following_url = model.followingUrl,
            gists_url = model.gistsUrl,
            gravatar_id = model.gravatarId,
            hireable = model.hireable,
            html_url = model.htmlUrl,
            id = model.id,
            location = model.location,
            login = model.login,
            name = model.name,
            node_id = model.nodeId,
            organizations_url = model.organizationsUrl,
            public_gists = model.publicGists,
            public_repos = model.publicRepos,
            received_events_url = model.receivedEventsUrl,
            repos_url = model.reposUrl,
            site_admin = model.siteAdmin,
            starred_url = model.starredUrl,
            subscriptions_url = model.subscriptionsUrl,
            twitter_username = model.twitterUsername,
            type = model.type,
            updated_at = model.updatedAt,
            url = model.url
        )
    }

    override fun mapFromDomainModel(domainModel: User): UserDto {
        return UserDto(
            avatarUrl = domainModel.avatar_url,
            bio = domainModel.bio,
            blog = domainModel.blog,
            company = domainModel.company,
            createdAt = domainModel.created_at,
            email = domainModel.email,
            eventsUrl = domainModel.events_url,
            followers = domainModel.followers,
            followersUrl = domainModel.followers_url,
            following = domainModel.following,
            followingUrl = domainModel.following_url,
            gistsUrl = domainModel.gists_url,
            gravatarId = domainModel.gravatar_id,
            hireable = domainModel.hireable,
            htmlUrl = domainModel.html_url,
            id = domainModel.id,
            location = domainModel.location,
            login = domainModel.login,
            name = domainModel.name,
            nodeId = domainModel.node_id,
            organizationsUrl = domainModel.organizations_url,
            publicGists = domainModel.public_gists,
            publicRepos = domainModel.public_repos,
            receivedEventsUrl = domainModel.received_events_url,
            reposUrl = domainModel.repos_url,
            siteAdmin = domainModel.site_admin,
            starredUrl = domainModel.starred_url,
            subscriptionsUrl = domainModel.subscriptions_url,
            twitterUsername = domainModel.twitter_username,
            type = domainModel.type,
            updatedAt = domainModel.updated_at,
            url = domainModel.url
        )
    }
}